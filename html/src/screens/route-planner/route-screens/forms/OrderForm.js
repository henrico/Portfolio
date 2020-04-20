import {
  SelectField,
  DateField,
  NumberField,
  FormValidator
} from '../../../../components/Forms/Forms.js';

const NProgress = require('nprogress/nprogress.js');

import notyf from '../../../../Notifications.js';

import server from '../../../../Server.js';

export default class OrderForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ports: [],
      products: []
    };

    this.validator = new FormValidator(this.props.updateForm);
    this.validator.createValidation('DESTINATION', value => {
      if (!value.toString().trim().length) return 'Destination is required.';
      return '';
    });
    this.validator.createValidation('PRODUCT', value => {
      if (!value.toString().trim().length) return 'Product is required.';
      return '';
    });
    this.validator.createValidation('QUANTITY', value => {
      if (!value.toString().trim().length) return 'Quantity is required.';
      if (value > 500) return 'Please enter a maximum number of 500.';
      if (value <= 0) return 'Please enter a minimum number of 1.';
      return '';
    });
    this.validator.createValidation('STATUS', value => {
      if (!value.toString().trim().length) return 'Order status is required.';
      return '';
    });
    this.validator.createValidation('COLLECTIONDATE', value => {
      if (!value.toString().trim().length)
        return 'Collection date is required.';
      return '';
    });
  }

  componentDidMount = () => {
    var self = this;

    NProgress.start();

    $.ajax({
      url: server + '/rest/port',
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        self.setState({
          ports: result
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });

    $.ajax({
      url: server + '/rest/product',
      type: 'GET',
      async: false,
      cache: false,
      timeout: 30000,
      success: function(result) {
        NProgress.done();
        self.setState({
          products: result
        });
      },
      error: function(result) {
        notyf.alert('Oops! Something went wrong!');
        NProgress.done();
      }
    });
  };

  setPort = value => {
    for (var z = 0; z < this.state.ports.length; z++) {
      if (this.state.ports[z].id == value) {
        this.props.order.destination = this.state.ports[z];
      }
    }
  };

  setProduct = value => {
    for (var z = 0; z < this.state.products.length; z++) {
      if (this.state.products[z].id == value) {
        this.props.order.product = this.state.products[z];
      }
    }
  };

  setStatus = value => {
    this.props.order.orderStatus = value;
  };

  render() {
    return (
      <div class="form-horizontal">
        <SelectField
          id={() => this.props.formid + '-destination'}
          label="Destination"
          disabled={!this.props.order.enabled}
          keyField="id"
          textField="name"
          data={this.state.ports}
          setOption={this.setPort}
          value={this.props.order.destination.id}
          validator={this.validator}
          validation="DESTINATION"
        />
        <DateField
          id={() => this.props.formid + '-deliveryDate'}
          label="Delivery Date"
          change={val => (this.props.order.deliveryDate = val)}
          value={this.props.order.deliveryDate}
          disabled={!this.props.order.enabled}
          validator={this.validator}
          validation="COLLECTIONDATE"
        />
        <SelectField
          id={() => this.props.formid + '-product'}
          label="Product"
          disabled={!this.props.order.enabled}
          keyField="id"
          textField="name"
          data={this.state.products}
          setOption={this.setProduct}
          value={this.props.order.product.id}
          validator={this.validator}
          validation="PRODUCT"
        />
        <NumberField
          id={() => this.props.formid + '-quantity'}
          label="Quantity (Crates)"
          change={val => (this.props.order.quantity = val)}
          value={this.props.order.quantity}
          disabled={!this.props.order.enabled}
          validator={this.validator}
          validation="QUANTITY"
          noDecimals={true}
        />
        <SelectField
          id={() => this.props.formid + '-orderStatus'}
          label="Status"
          disabled={!this.props.order.enabled}
          keyField="id"
          textField="name"
          data={[
            {
              id: 'PLACED',
              name: 'Placed'
            },
            {
              id: 'IN_TRANSIT',
              name: 'In Transit'
            },
            {
              id: 'COMPLETTE',
              name: 'Complette'
            }
          ]}
          setOption={this.setStatus}
          value={this.props.order.orderStatus}
          validator={this.validator}
          validation="STATUS"
        />
      </div>
    );
  }
}
