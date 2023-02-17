import Entities from './Entities.js';

import RouteForm from './forms/RouteForm.js';
import { textFilter, numberFilter } from 'react-bootstrap-table2-filter';

const destFormatter = name => {
  return <div>{name}</div>;
};

const columns = [
  {
    dataField: 'destinationA',
    text: 'Port 1',
    filter: textFilter({
      placeholder: 'Filter Port 1'
    }),
    formatter: (cell, row) => destFormatter(row.destinationA.name)
  },
  {
    dataField: 'destinationB',
    text: 'Port 2',
    filter: textFilter({
      placeholder: 'Filter Port 2'
    }),
    formatter: (cell, row) => destFormatter(row.destinationB.name)
  },
  {
    dataField: 'distance',
    text: 'Distance',
    filter: numberFilter({
      placeholder: '0.00'
    })
  }
];

export default class Routes extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, route, key, callback) {
    return (
      <RouteForm
        key={key}
        formId={'update-' + updateId}
        route={route}
        updateForm={callback}
      />
    );
  }

  newRoute() {
    return {
      destinationA: {},
      destinationB: {},
      distance: 0,
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/route"
        entityName="Route"
        heading="Routes"
        description="Add Routes to connect two Ports."
        form={this.form}
        newEntity={this.newRoute}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
