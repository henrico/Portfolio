import Screens from './Screens.js';

import SideMenu from './screens/SideMenu.js';

import Introduction from './screens/introduction/Introduction.js';
import RoutePlanner from './screens/route-planner/RoutePlanner.js';
import NeuralNet from './screens/neural-net/NeuralNet.js';

export default class Content extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      active: Screens.INTRODUCTION
    };
  }

  setActive(active) {
    this.setState({
      active: active
    });
  }

  render() {
    let content;

    switch (this.state.active) {
      case Screens.INTRODUCTION:
        content = <Introduction />;
        break;
      case Screens.ROUTEPLANNER:
        content = <RoutePlanner />;
        break;
      case Screens.NEURALNET:
        content = <NeuralNet />;
        break;
      default:
    }

    return (
      <div class="wrapper">
        <SideMenu owner={this} />
        {content}
      </div>
    );
  }
}
