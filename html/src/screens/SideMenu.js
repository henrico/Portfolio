import Screens from '../Screens.js';

export default class SideMenu extends React.Component {
  constructor(props) {
    super(props);
  }

  setActive(active) {
    this.props.owner.setActive(active);
  }

  render() {
    return (
      <nav id="sidebar">
        <ul class="list-unstyled components">
          <li>
            <a onClick={() => this.setActive(Screens.INTRODUCTION)}>Home</a>
          </li>
          <li>
            <a onClick={() => this.setActive(Screens.ROUTEPLANNER)}>
              Route Planner
            </a>
          </li>

          <li>
            <a href="javascript:window.open('https://github.com/henrico/Portfolio')">
              Source Code
            </a>
          </li>
        </ul>
      </nav>
    );
  }
}
