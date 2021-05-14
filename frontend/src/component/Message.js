import React, {Component} from 'react';
import './Message.css'

class Message extends Component {
    render() {
        const message = this.props.content !== '' ? `${this.props.content}` : '';
        const show = message == '' ? 'hidden' : 'show';
        return (
            <div className={'message ' + show}>
                {message}
            </div>
        );
    }
}

export default Message;