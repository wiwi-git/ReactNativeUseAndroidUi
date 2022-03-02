import React from 'react';
import PropTypes from 'prop-types';

class MyCustomView extends React.Component {
  constructor(props) {
    super(props);
    this._onChange = this._onChange.bind(this);
  }
  _onChange(evnet) {
    if (!this.props.onChangeMessage) {
      return;
    }

    this.props.onChangeMessage(event.nativeEvent.message);
  }
}

MyCustomView.propTypes = {
  /**
   * Callback that is called continuously when the user is dragging the map.
   */
  onChangeMessage: PropTypes.func,
};

const RCTMyCustomView = requireNativeComponent(`RCTMyCustomView`);
