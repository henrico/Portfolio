export default class Introduction extends React.Component {
	render() {
		return (
			<div class="home">
				<h2>Overview</h2>
				<p>My name is Henrico Robinson, and I am a skilled full-stack software developer.</p>

				<p>
					As part of my portfolio, I have created the below example that demonstrates my abilities
					in software development.
				</p>

				<h2>Route Planner</h2>
				<p>
					One example of my work is a fictitious application that I developed using Java EE and REST
					interfaces on the back-end, primarily using Spring-based frameworks. The user interface was
					created with React and styled with Bootstrap.
				</p>

				<h4>How to use it</h4>

				<p>
					This application is designed to schedule routes for trade ships. Users can add elements to a
					product shipping route, and once all items have been added, a schedule can be created. The
					application then uses speed and availability to determine the warehouse and ship that will
					fulfill and deliver the order on time.
				</p>

				<h2>Source Code</h2>
				<p>
					If you are interested in exploring the source code for this and other projects that I have worked
					on, you can find them on my GitHub profile:<br/>
					<a href="https://github.com/henrico/Portfolio" target="_blank" rel="noopener noreferrer">
						https://github.com/henrico/Portfolio
					</a>
					.
				</p>
			</div>
		);
	}
}
