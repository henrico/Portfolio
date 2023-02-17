import Entities from './Entities.js';

import ShipForm from './forms/ShipForm.js';
import { textFilter, numberFilter } from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'name',
    text: 'Name',
    filter: textFilter({
      placeholder: 'Filter Name'
    })
  },
  {
    dataField: 'speed',
    text: 'Speed',
    filter: numberFilter({
      placeholder: '0.00'
    })
  },
  {
    dataField: 'capacity',
    text: 'Capacity',
    filter: numberFilter({
      placeholder: '0'
    })
  }
];

export default class Ships extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, ship, key, callback) {
    return (
      <ShipForm
        key={key}
        formId={'update-' + updateId}
        ship={ship}
        updateForm={callback}
      />
    );
  }

  newShip() {
    return {
      name: '',
      speed: 0,
      capacity: 0,
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/ship"
        entityName="Ship"
        heading="Ships"
        description="Ships are used on Schedules to fulfill product Orders."
        form={this.form}
        newEntity={this.newShip}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
