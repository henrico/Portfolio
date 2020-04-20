import server from '../../server.js';

import './NeuralNet.css';

const NProgress = require('nprogress/nprogress.js');

import notyf from '../../Notifications.js';

import { SketchPicker } from 'react-color';

export default class NeuralNet extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      colors: [],
      scheme: {},
      training: false,
      iteration: 0,
      error: 0,
      color: { r: 50, g: 100, b: 30 }
    };
  }

  componentDidMount = () => {
    this.isTraining();
  };

  isTraining = () => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'GET',
      url: server + '/colors/training',
      contentType: 'application/json',
      success: function(result) {
        NProgress.done();
        self.setState({
          training: result.training,
          iteration: result.interation,
          error: result.errorFactor
        });

        if (!result.training) {
          self.getColors();
        } else {
          setTimeout(self.isTraining, 500);
        }
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  getColors = () => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'GET',
      url: server + '/colors',
      contentType: 'application/json',
      success: function(result) {
        NProgress.done();
        self.getRandom();
        self.setState({
          colors: result
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  getRandom = () => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'GET',
      url: server + '/colors/random',
      contentType: 'application/json',
      success: function(result) {
        NProgress.done();
        self.setState({
          scheme: result
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  getScheme = () => {
    var self = this;

    NProgress.start();
    $.ajax({
      type: 'GET',
      url:
        server +
        '/colors/fromcolor/' +
        this.state.color.r +
        '/' +
        this.state.color.g +
        '/' +
        this.state.color.b,
      contentType: 'application/json',
      success: function(result) {
        NProgress.done();
        self.setState({
          scheme: result
        });
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  changeNetwork = type => {
    var self = this;

    self.setState({
      training: true,
      iteration: 0,
      error: 0
    });

    NProgress.start();
    $.ajax({
      type: 'GET',
      url: server + '/colors/changeSceme/' + type,
      contentType: 'application/json',
      success: function(result) {
        NProgress.done();
        self.isTraining();
      },
      error: function(result) {
        NProgress.done();
        notyf.alert('Oops! Something went wrong!');
      }
    });
  };

  render() {
    if (this.state.training)
      return (
        <div>
          <h3>Color Scheme Generator</h3>
          <p>The Network is currently training.</p>
          <p>Iteration: {this.state.iteration}</p>
          <p>Network Error: {this.state.error}</p>
        </div>
      );
    else
      return (
        <div>
          <h3>Color Scheme Generator</h3>
          <p>
            The color scheme is generated using a Perceptron Neural
            Network.
          </p>
          <div>
            <button
              class="generate-button btn btn-dark"
              onClick={this.getRandom}
            >
              Generate Random Color Scheme
            </button>{' '}
            <button
              class="generate-button btn btn-dark"
              onClick={this.getScheme}
            >
              Generate From Color Picker
            </button>{' '}
            <SketchPicker
              color={this.state.color}
              onChangeComplete={color => {
                this.setState({
                  color: color.rgb
                });
              }}
              disableAlpha={true}
            /><br/>
            <br/>
            {(() => {
              if (this.state.scheme.colors) {
                return (
                  <table class="training-table">
                    <tr>
                      {this.state.scheme.colors.map((color, z) => {
                        return (
                          <td class="trainin-table-td" key={z}>
                            <div
                              class="trainin-table-div"
                              style={{
                                backgroundColor:
                                  'rgb(' +
                                  color.r +
                                  ', ' +
                                  color.g +
                                  ', ' +
                                  color.b +
                                  ')'
                              }}
                            >
                              &nbsp;
                            </div>
                          </td>
                        );
                      })}
                    </tr>
                  </table>
                );
              }
            })()}
          </div>
          <br />
          <h3>Training the Network</h3>

          <p>
            The Neural Network learns how to generate a color scheme by
            analyzing the relationships between the colors' red, green and blue (RGB)
            values.
          </p>
          <p>
            A training color scheme (a single row of training data) is sorted using each of the colors' red
            value. Then the varaince of the reds is calculated.
          </p>
          <p>
            The net has 21 inputs per row:
            <ul>
              <li>1 for the red variance.</li>
              <li>15 RGB values (3 values for each of the 5 colors).</li>
              <li>5 boolean indicators (0/1) that indicate which color is used as the base color. Only one color acts as an input at a time.
              </li>
            </ul>

          </p>
          <br />
          <br />
          Generate new training data based on one of the 3 different colour types and retrain the network using the new set:
          <br />
          <br />
          <div class="btn-group">
            <button
              class="generate-button btn btn-dark"
              onClick={() => this.changeNetwork(1)}
            >
              1.Color Wheel
            </button>
            <button
              class="generate-button btn btn-dark"
              onClick={() => this.changeNetwork(2)}
            >
              2.Tints and Shades
            </button>
            <button
              class="generate-button btn btn-dark"
              onClick={() => this.changeNetwork(3)}
            >
              3.Tones
            </button>
          </div>
          <br />
          <br />
          When the new set has been generated, try clicking the <b>Generate Color Scheme</b> buttons above again.
          <br />
          <br />
          <h3>Training Data</h3>
          <table class="training-table">
            {this.state.colors.map((object, i) => {
              let colors = object.colors;
              return (
                <tr key={i}>
                  {colors.map((color, z) => {
                    return (
                      <td class="trainin-table-td" key={z}>
                        <div
                          class="trainin-table-div"
                          style={{
                            backgroundColor:
                              'rgb(' +
                              color.r +
                              ', ' +
                              color.g +
                              ', ' +
                              color.b +
                              ')'
                          }}
                        >
                          &nbsp;
                        </div>
                      </td>
                    );
                  })}
                </tr>
              );
            })}
          </table>
        </div>
      );
  }
}
