import {
  SelectField,
  DateField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

import { textFilter } from 'react-bootstrap-table2-filter';

const NProgress = require('nprogress/nprogress.js');

import server from '../../../../Server.js';
import notyf from '../../../../Notifications.js';

export default class ScheduleForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      sourceVisible: false,
      shipVisible: false,
      warehouseVisible: false,
      orders: [],
      ports: [],
      ships: [],
      warehouses: [],
      portKey: 0,
      shipKey: 0,
      warehouseKey: 0
    };

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('ORDER', value => {
      if (!value.toString().trim().length) return 'Order is required.';
      return '';
    });
    this.validator.createValidation('COLLECTIONDATE', value => {
      if (!value.toString().trim().length)
        return 'Collection date is required.';
      return '';
    });
    this.validator.createValidation('SOURCE', value => {
      if (!value.toString().trim().length) return 'From port is required.';
      return '';
    });
    this.validator.createValidation('SHIP', value => {
      if (!value.toString().trim().length) return 'Ship is required.';
      return '';
    });
    this.validator.createValidation('WAREHOUSE', value => {
      if (!value.toString().trim().length) return 'Warehouse is required.';
      return '';
    });
  }

  componentDidMount = () => {
    var self = this;

    NProgress.start();

    $.ajax({
      url: server + '/rest/schedule/unfilledOrders',
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        if (result.length == 0) notyf.warn('There are no unfilled Orders.');

        result.forEach(item => {
          item.description =
            item.id +
            ' - ' +
            item.quantity +
            ' Crates of ' +
            item.product.name +
            ' by ' +
            item.deliveryDate;
        });

        self.setState({
          orders: result
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setOrder = value => {
    if (value === '') this.props.schedule.order = {};
    else
      for (var z = 0; z < this.state.orders.length; z++) {
        if (this.state.orders[z].id == value) {
          this.props.schedule.order = this.state.orders[z];
          break;
        }
      }

    var self = this;

    this.props.schedule.source = {};
    this.props.schedule.ship = {};
    this.props.schedule.warehouse = {};

    this.validator.reset('SOURCE');
    this.validator.reset('SHIP');
    this.validator.reset('WAREHOUSE');

    if (!this.props.schedule.order.id) {
      self.setState({
        sourceVisible: false,
        shipVisible: false,
        warehouseVisible: false,
        portKey: self.state.portKey + 1,
        shipKey: self.state.shipKey + 1,
        warehouseKey: self.state.warehouseKey + 1
      });
      return;
    }

    NProgress.start();

    $.ajax({
      url:
        server +
        '/rest/schedule/portsProducingProduct/' +
        this.props.schedule.order.product.id,
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        if (result.length == 0)
          notyf.warn('There are no ports producing this product.');
        self.setState({
          ports: result,
          sourceVisible: value != '',
          shipVisible: false,
          warehouseVisible: false,
          portKey: self.state.portKey + 1,
          shipKey: self.state.shipKey + 1,
          warehouseKey: self.state.warehouseKey + 1
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setPort = value => {
    if (value === '') this.props.schedule.source = {};
    else
      for (var z = 0; z < this.state.ports.length; z++) {
        if (this.state.ports[z].id == value) {
          this.props.schedule.source = this.state.ports[z];
          break;
        }
      }

    this.props.schedule.ship = {};
    this.props.schedule.warehouse = {};

    this.validator.reset('SHIP');
    this.validator.reset('WAREHOUSE');

    this.loadShips();
  };

  loadShips = () => {
    var self = this;
    if (
      !self.props.schedule.source.id ||
      self.props.schedule.collectionDate == ''
    ) {
      self.setState({
        shipVisible: false,
        warehouseVisible: false,
        shipKey: self.state.shipKey + 1,
        warehouseKey: self.state.warehouseKey + 1
      });
      return;
    }

    NProgress.start();

    $.ajax({
      url:
        server +
        '/rest/schedule/shipsForOrder/' +
        this.props.schedule.order.id +
        '/' +
        this.props.schedule.source.id +
        '/' +
        this.props.schedule.collectionDate,
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        if (result.length == 0)
          notyf.warn(
            'There are no ships that could deliver this order in time. Try moving the order to a future date or adding a faster ship.'
          );
        self.setState({
          ships: result,
          shipVisible:
            self.props.schedule.source.id &&
            self.props.schedule.collectionDate != '',
          warehouseVisible: false,
          shipKey: self.state.shipKey + 1,
          warehouseKey: self.state.warehouseKey + 1
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setShip = value => {
    if (value === '') this.props.schedule.ship = {};
    else
      for (var z = 0; z < this.state.ships.length; z++) {
        if (this.state.ships[z].id == value) {
          this.props.schedule.ship = this.state.ships[z];
          break;
        }
      }

    this.props.schedule.warehouse = {};

    this.validator.reset('WAREHOUSE');

    var self = this;

    if (!this.props.schedule.ship.id) {
      self.setState({
        warehouseVisible: false,
        warehouseKey: self.state.warehouseKey + 1
      });
      return;
    }

    NProgress.start();

    $.ajax({
      url:
        server +
        '/rest/schedule/warehousesForOrder/' +
        this.props.schedule.order.id +
        '/' +
        this.props.schedule.source.id +
        '/' +
        this.props.schedule.ship.id +
        '/' +
        this.props.schedule.collectionDate,
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        if (result.length == 0)
          notyf.warn(
            'There are no warehouses with the capacity to store the product within the given time frame.'
          );
        self.setState({
          warehouses: result,
          warehouseVisible: value != '',
          warehouseKey: self.state.warehouseKey + 1
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setWarehouse = value => {
    if (value === '') this.props.schedule.warehouse = {};
    else
      for (var z = 0; z < this.state.warehouses.length; z++) {
        if (this.state.warehouses[z].id == value) {
          this.props.schedule.warehouse = this.state.warehouses[z];
          break;
        }
      }
  };

  render() {
    return (
      <div class="form-horizontal">
        <SelectField
          id={() => this.props.formid + '-order'}
          label="Order"
          disabled={!this.props.schedule.enabled}
          keyField="id"
          textField="description"
          data={this.state.orders}
          setOption={this.setOrder}
          value={this.props.schedule.order.id}
          validator={this.validator}
          validation="ORDER"
        />
        <DateField
          id={() => this.props.formid + '-collectionDate'}
          label="Collection Date"
          change={val => {
            this.props.schedule.collectionDate = val;
            this.props.schedule.ship = {};
            this.props.schedule.warehouse = {};
            this.validator.reset('SHIP');
            this.validator.reset('WAREHOUSE');
            this.loadShips();
          }}
          value={this.props.schedule.collectionDate}
          disabled={!this.props.schedule.enabled}
          validator={this.validator}
          validation="COLLECTIONDATE"
        />
        {(() => {
          if (this.state.sourceVisible)
            return (
              <SelectField
                id={() => this.props.formid + '-source'}
                label="From Port"
                disabled={!this.props.schedule.enabled}
                keyField="id"
                textField="name"
                data={this.state.ports}
                setOption={this.setPort}
                value={this.props.schedule.source.id}
                validator={this.validator}
                validation="SOURCE"
                key={this.state.portKey}
              />
            );
        })()}
        {(() => {
          if (this.state.sourceVisible && this.state.shipVisible)
            return (
              <SelectField
                id={() => this.props.formid + '-ship'}
                label="Ship"
                disabled={!this.props.schedule.enabled}
                keyField="id"
                textField="name"
                data={this.state.ships}
                setOption={this.setShip}
                value={this.props.schedule.ship.id}
                validator={this.validator}
                validation="SHIP"
                key={this.state.shipKey}
              />
            );
        })()}
        {(() => {
          if (
            this.state.sourceVisible &&
            this.state.shipVisible &&
            this.state.warehouseVisible
          )
            return (
              <SelectField
                id={() => this.props.formid + '-warehouse'}
                label="Warehouse"
                disabled={!this.props.schedule.enabled}
                keyField="id"
                textField="name"
                data={this.state.warehouses}
                setOption={this.setWarehouse}
                value={this.props.schedule.warehouse.id}
                validator={this.validator}
                validation="WAREHOUSE"
                key={this.state.warehouseKey}
              />
            );
        })()}
      </div>
    );
  }
}
