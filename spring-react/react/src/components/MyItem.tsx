import React from 'react';

interface IProps {
    value: string
}

class MyItem extends React.Component<IProps, {}> {
    render() {
        return <li>{this.props.value}</li>
    }
}

export default MyItem
