import { IoIosArrowForward } from 'react-icons/io';
import { IoIosArrowDown } from 'react-icons/io';

import { FaEdit } from 'react-icons/fa';
import { FaTrashAlt } from 'react-icons/fa';

import BootstrapTable from 'react-bootstrap-table-next';
import filterFactory from 'react-bootstrap-table2-filter';

import Confirm from 'react-confirm-bootstrap';

import './Table.css';

function expandedTableIcon(expanded, key) {
  if (!expanded)
    return (
      <div class="detail-icon" id={'expand-' + key}>
        <IoIosArrowForward class="clickable-icon" />
      </div>
    );
  return (
    <div class="detail-icon">
      <IoIosArrowDown class="clickable-icon" />
    </div>
  );
}

export class Table extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      columns: this.props.columns.slice()
    };

    this.state.columns.push({
      dataField: '',
      text: '',
      formatter: (cell, row) => {
        let edit;

        if (this.props.allowEdit) {
          edit = (
            <a onClick={() => this.editRow(row)}>
              <FaEdit class="clickable-icon" />
            </a>
          );
        }

        return (
          <div class="detail-icon">
            {edit}
            <Confirm
              onConfirm={() => {
                this.props.delete(row);
              }}
              body="Are you sure you want to delete this?"
              confirmText="Confirm Delete"
              title={'Deleting ' + this.props.entityName}
            >
              <button class="icon-button">
                <FaTrashAlt />
              </button>
            </Confirm>
          </div>
        );
      }
    });
  }

  editRow = row => {
    $('#expand-' + row[this.props.keyField]).click();
    this.props.editRow(row);
  };

  expandRow = renderer => {
    return {
      renderer: renderer,
      showExpandColumn: true,
      expandByColumnOnly: true,
      expandColumnRenderer: val => {
        return expandedTableIcon(val.expanded, val.rowKey);
      },
      expandHeaderColumnRenderer: val => {
        return expandedTableIcon(val.isAnyExpands);
      },
      onExpand: (row, isExpand) => {
        this.props.expandedMap.set(row[this.props.keyField], isExpand);
      },
      onExpandAll: isExpandAll => {
        this.props.data.forEach(item => {
          this.props.expandedMap.set(item[this.props.keyField], isExpandAll);
        });
      },
      expanded: (() => {
        var ret = [];

        this.props.data.forEach(item => {
          if (this.props.expandedMap.get(item[this.props.keyField])) {
            ret.push(item[this.props.keyField]);
          }
        });
        return ret;
      })()
    };
  };

  render = () => {
    if (this.props.allowExpand) {
      return (
        <BootstrapTable
          key={this.props.tableKey}
          keyField={this.props.keyField}
          data={this.props.data}
          columns={this.state.columns}
          expandRow={this.expandRow(this.props.expandRow())}
          filter={filterFactory()}
        />
      );
    } else {
      return (
        <BootstrapTable
          key={this.props.tableKey}
          keyField={this.props.keyField}
          data={this.props.data}
          columns={this.state.columns}
          filter={filterFactory()}
        />
      );
    }
  };
}

export default Table;
