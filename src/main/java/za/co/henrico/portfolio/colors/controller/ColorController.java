package za.co.henrico.portfolio.colors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.henrico.portfolio.colors.service.SchemeService;
import za.co.henrico.portfolio.colors.model.Scheme;
import java.util.Collection;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.learning.BackPropagation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/colors")
@CrossOrigin(origins = "*")
public class ColorController implements LearningEventListener {

	@Autowired
	protected SchemeService schemeService;

	private MultiLayerPerceptron neuralNet;

	private String status = "generating";

	private Random rand = new Random();

	private int iteration;
	private String error;
	private boolean done = false;

	private int type=1;

	@PostConstruct
	public void init(){

		neuralNet = new MultiLayerPerceptron((1 + (3*5) + 5), 15, (3*5));
		done = false;

		schemeService.deleteAll();

		DataSet trainingSet = new DataSet((1 + (3*5) + 5),(3*5));

		for (int r=0; r<100; r++) {

			  Scheme s = null;
			  if (type==1) {
				    s = Scheme.createRandomColorWheel();
			    }
			  if (type==2) {
				    s = Scheme.createTintsAndShades();
			    }
			  if (type==3) {
				    s = Scheme.createTones();
			    }

			  schemeService.save(s);

			  for (DataSetRow cur : s.getDatasetRows()) {
				    trainingSet.addRow(cur);
			    }


		  }

		MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();

		learningRule.setLearningRate(0.05);
		learningRule.setMaxError(0.001);
		learningRule.setMaxIterations(1000);

		learningRule.addListener(this);

		System.out.println("Training network...");
		neuralNet.learnInNewThread(trainingSet);

	}

	@RequestMapping("")
	public @ResponseBody Collection<Scheme> getList() {
		return schemeService.getList();
	}

	@RequestMapping("/changeSceme/{type}")
	public @ResponseBody void changeType(@PathVariable("type") int type) {
		this.type=type;
		init();
	}

	@RequestMapping("/training")
	public @ResponseBody StatusDto isTraining() {

		StatusDto ret = new StatusDto();

		ret.training=!done;
		ret.interation=iteration;
		ret.errorFactor=error;

		return ret;
	}

	@RequestMapping("/random")
	public @ResponseBody Scheme getRandom() {
		double[] input = new double[(1 + (3*5) + 5)];

		input[0] = rand.nextDouble();

		int c = rand.nextInt(4);

		input[1+(c*4)] = 1;
		input[2+(c*4)] = rand.nextDouble();
		input[3+(c*4)] = rand.nextDouble();
		input[4+(c*4)] = rand.nextDouble();

		neuralNet.setInput(input);
		neuralNet.calculate();

		return new Scheme(neuralNet.getOutput());
	}

	@RequestMapping("/fromcolor/{r}/{g}/{b}")
	public @ResponseBody Scheme getRandom(@PathVariable("r") int r,@PathVariable("g") int g,@PathVariable("b") int b) {
		double[] input = new double[(1 + (3*5) + 5)];

		input[0] = rand.nextDouble();

		int c = rand.nextInt(4);

		input[1+(c*4)] = 1;
		input[2+(c*4)] = ((double)r)/255;
		input[3+(c*4)] = ((double)g)/255;
		input[4+(c*4)] = ((double)b)/255;

		neuralNet.setInput(input);
		neuralNet.calculate();

		double[] output = neuralNet.getOutput();

		// output[(c*3)] = ((double)r)/255;
		// output[1+(c*3)] = ((double)g)/255;
		// output[2+(c*3)] = ((double)b)/255;

		return new Scheme(output);
	}

	@Override
	public void handleLearningEvent(LearningEvent event) {
		BackPropagation bp = (BackPropagation) event.getSource();
		error = formatDecimalNumber(bp.getTotalNetworkError());
		iteration = bp.getCurrentIteration();
		if (event.getEventType().equals(LearningEvent.Type.LEARNING_STOPPED)) {
			  done=true;

		  } else {
			  // System.out.println("Iteration: " + bp.getCurrentIteration() + " | Network error: " + formatDecimalNumber(bp.getTotalNetworkError()));
		  }
	}

//Formating decimal number to have 3 decimal places
	public String formatDecimalNumber(double number) {
		return new BigDecimal(number).setScale(4, RoundingMode.HALF_UP).toString();
	}

}
