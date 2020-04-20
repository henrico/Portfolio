import Products from './route-screens/Products.js';
import Ports from './route-screens/Ports.js';
import Warehouses from './route-screens/Warehouses.js';
import Routes from './route-screens/Routes.js';
import Ships from './route-screens/Ships.js';
import Orders from './route-screens/Orders.js';
import Schedules from './route-screens/Schedules.js';

import enumValue from '../../EnumValue.js';

const RouteScreens = Object.freeze({
  PRODUCTS: enumValue('Screens.PRODUCTS'),
  PORTS: enumValue('Screens.PORTS'),
  WAREHOUSES: enumValue('Screens.WAREHOUSES'),
  ROUTES: enumValue('Screens.ROUTES'),
  SHIPS: enumValue('Screens.SHIPS'),
  ORDERS: enumValue('Screens.ORDERS'),
  SCHEDULES: enumValue('Screens.SCHEDULES')
});

export default class RoutePlanner extends React.Component {
  constructor() {
    super();
    this.state = {
      active: RouteScreens.PRODUCTS
    };
  }

  getClass(screen) {
    if (screen === this.state.active) return 'active';
    return '';
  }

  setActive(active) {
    this.setState({
      active: active
    });
  }

  render() {
    return (
      <div>
        <div id="content">
          <nav class="navbar nav-wizard">
            <div class="container-fluid">
              <ul class="nav nav-wizard">
                <li className={this.getClass(RouteScreens.PRODUCTS)}>
                  <a onClick={() => this.setActive(RouteScreens.PRODUCTS)}>
                    Products
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.PORTS)}>
                  <a onClick={() => this.setActive(RouteScreens.PORTS)}>
                    Ports
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.WAREHOUSES)}>
                  <a onClick={() => this.setActive(RouteScreens.WAREHOUSES)}>
                    Warehouses
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.ROUTES)}>
                  <a onClick={() => this.setActive(RouteScreens.ROUTES)}>
                    Routes
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.SHIPS)}>
                  <a onClick={() => this.setActive(RouteScreens.SHIPS)}>
                    Ships
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.ORDERS)}>
                  <a onClick={() => this.setActive(RouteScreens.ORDERS)}>
                    Orders
                  </a>
                </li>
                <li className={this.getClass(RouteScreens.SCHEDULES)}>
                  <a onClick={() => this.setActive(RouteScreens.SCHEDULES)}>
                    Schedules
                  </a>
                </li>
              </ul>
            </div>
          </nav>
        </div>
        <div>
          {
            {
              'Screens.PRODUCTS': <Products />,
              'Screens.PORTS': <Ports />,
              'Screens.WAREHOUSES': <Warehouses />,
              'Screens.ROUTES': <Routes />,
              'Screens.SHIPS': <Ships />,
              'Screens.ORDERS': <Orders />,
              'Screens.SCHEDULES': <Schedules />
            }[this.state.active.toString()]
          }
        </div>
      </div>
    );
  }
}
