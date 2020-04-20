import Entities from './Entities.js';

import OrderForm from './forms/OrderForm.js';
import {
  textFilter,
  numberFilter,
  selectFilter
} from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'id',
    text: 'Order Number',
    filter: numberFilter({
      placeholder: '0'
    })
  },
  {
    dataField: 'destination',
    text: 'Destination',
    filter: textFilter({
      placeholder: 'Filter Destination'
    }),
    formatter: (cell, row) => {
      return <div>{row.destination.name}</div>;
    }
  },
  {
    dataField: 'product',
    text: 'Product',
    filter: textFilter({
      placeholder: 'Filter Product'
    }),
    formatter: (cell, row) => {
      return <div>{row.product.name}</div>;
    }
  },
  {
    dataField: 'quantity',
    text: 'Quantity',
    filter: numberFilter({
      placeholder: '0'
    })
  },
  {
    dataField: 'deliveryDate',
    text: 'Delivery Date',
    filter: textFilter({
      placeholder: 'Filter Delivery Date'
    }),
    formatter: (cell, row) => {
      var d = new Date(row.deliveryDate),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

      return [year, month, day].join('-');
    }
  },
  {
    dataField: 'orderStatus',
    text: 'Status',
    filter: selectFilter({
      options: {
        PLACED: 'Placed',
        IN_TRANSIT: 'In Transit',
        COMPLETTE: 'Complette'
      }
    })
  }
];

export default class Orders extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, order, key, callback) {
    return (
      <OrderForm
        key={key}
        formId={'update-' + updateId}
        order={order}
        updateForm={callback}
      />
    );
  }

  newOrder() {
    return {
      name: '',
      destination: {},
      deliveryDate: '',
      product: {},
      quantity: 0,
      orderStatus: '',
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/order/"
        entityName="Order"
        heading="Orders"
        description="Orders of Products are sent to Ports and are fulfilled by Schedules."
        form={this.form}
        newEntity={this.newOrder}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
