export default class Heading extends React.Component {

  constructor(props){
    super(props);
  }

  render() {

    return (
      <h1>
        {this.props.heading}
      </h1>
    );

  }

}
