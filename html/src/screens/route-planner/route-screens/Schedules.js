import Entities from './Entities.js';

import ScheduleForm from './forms/ScheduleForm.js';
import { textFilter, numberFilter } from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: '',
    text: 'Order Number',
    filter: numberFilter({
      placeholder: '0'
    }),
    formatter: (col, row) => {
      return <div>{row.order.id}</div>;
    }
  },
  {
    dataField: '',
    text: 'From Port',
    filter: textFilter({
      placeholder: 'Filter From Port'
    }),
    formatter: (col, row) => {
      return <div>{row.source.name}</div>;
    }
  },
  {
    dataField: '',
    text: 'To Port',
    filter: textFilter({
      placeholder: 'Filter To Port'
    }),
    formatter: (col, row) => {
      return <div>{row.order.destination.name}</div>;
    }
  },
  {
    dataField: '',
    text: 'Ship',
    filter: textFilter({
      placeholder: 'Filter Ship'
    }),
    formatter: (col, row) => {
      return <div>{row.ship.name}</div>;
    }
  },
  {
    dataField: '',
    text: 'Collection Date',
    filter: textFilter({
      placeholder: 'Filter Collection Date'
    }),
    formatter: (cell, row) => {
      var d = new Date(row.collectionDate),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

      return [year, month, day].join('-');
    }
  },
  {
    dataField: '',
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
    dataField: 'storedCrates',
    text: 'Stored Crates',
    filter: numberFilter({
      placeholder: '0'
    })
  },
  {
    dataField: 'cost',
    text: 'Cost',
    filter: numberFilter({
      placeholder: '0.00'
    })
  }
];

export default class Schedules extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, schedule, key, callback) {
    return (
      <ScheduleForm
        key={key}
        formId={'update-' + updateId}
        schedule={schedule}
        updateForm={callback}
      />
    );
  }

  newSchedule() {
    return {
      order: {},
      collectionDate: '',
      source: {},
      ship: {},
      warehouse: {},
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/schedule/"
        entityName="Schedule"
        heading="Schedules"
        description="Select an Order to be shipped. Only Ships that can meet the delivery date are available."
        form={this.form}
        newEntity={this.newSchedule}
      />
    );
  }
}
