import './EntityIntroduction.css';
import Heading from './Heading.js';
import Description from './Description.js';

export default class EntityIntroduction extends React.Component {

  constructor(props){
    super(props);
  }

  render() {

    return (
      <div class="row introduction-row">
        <div class="col-sm-12 introduction-col">
          <Heading heading = {this.props.heading} />
          <Description description = {this.props.description} />
        </div>
      </div>
    );

  }

}
