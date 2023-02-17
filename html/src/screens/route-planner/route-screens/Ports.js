import Entities from './Entities.js';

import PortForm from './forms/PortForm.js';
import { textFilter } from 'react-bootstrap-table2-filter';

const columns = [
  {
    dataField: 'name',
    text: 'Name',
    filter: textFilter({
      placeholder: 'Filter Name'
    })
  }
];

export default class Ports extends React.Component {
  constructor() {
    super();
    this.state = {
      columns: columns
    };
  }

  form(updateId, port, key, callback) {
    return (
      <PortForm
        key={key}
        formId={'update-' + updateId}
        port={port}
        updateForm={callback}
      />
    );
  }

  newPort() {
    return {
      name: '',
      products: [],
      enabled: true,
      valid: false
    };
  }

  render() {
    return (
      <Entities
        columns={this.state.columns}
        keyField="id"
        rest="/rest/port"
        entityName="Port"
        heading="Ports"
        description="Indicate which Products your Ports produce. Then link Warehouses to a Port."
        form={this.form}
        newEntity={this.newPort}
        allowExpand={true}
        allowEdit={true}
      />
    );
  }
}
