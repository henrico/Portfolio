import Entities from './Entities.js';

import WarehouseForm from './forms/WarehouseForm.js';
import {
  textFilter,
  numberFilter,
  selectFilter
} from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'name',
    text: 'Name',
    filter: textFilter({
      placeholder: 'Filter Name'
    })
  },
  {
    dataField: 'port',
    text: 'Port',
    filter: textFilter({
      placeholder: 'Filter Port'
    }),
    formatter: (cell, row) => {
      return <div>{row.port.name}</div>;
    }
  },
  {
    dataField: 'type',
    text: 'Type',
    filter: selectFilter({
      options: { INTERNAL: 'Internal', EXTERNAL: 'External' },
      placeholder: 'Filter Type'
    })
  },
  {
    dataField: 'capacity',
    text: 'Capacity',
    filter: numberFilter({
      placeholder: '0'
    })
  },
  {
    dataField: 'storageCost',
    text: 'Storage Cost',
    filter: numberFilter({
      placeholder: '0.00'
    })
  },
  {
    dataField: 'transportCost',
    text: 'Transport Cost',
    filter: numberFilter({
      placeholder: '0.00'
    })
  }
];

export default class Warehouses extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, warehouse, key, callback) {
    return (
      <WarehouseForm
        key={key}
        formId={'update-' + updateId}
        warehouse={warehouse}
        updateForm={callback}
      />
    );
  }

  newWarehouse() {
    return {
      name: '',
      type: '',
      capacity: '',
      storageCost: '',
      port: {},
      transportCost: '',
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/warehouse"
        entityName="Warehouse"
        heading="Warehouses"
        description="Add a Warehouse and select a Port it will supply."
        form={this.form}
        newEntity={this.newWarehouse}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
