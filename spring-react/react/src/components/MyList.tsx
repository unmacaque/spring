import React from 'react';
import MyItem from './MyItem'
import '../App.css';

interface IState {
    items: string[];
}

class MyList extends React.Component<any, IState> {
    constructor(props: any) {
        super(props);
        this.state = { items: [] };
    }

    componentDidMount() {
        fetch('/api/')
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.statusText);
                }
                return response.json()
            })
            .then((data: string[]) => {
                this.setState({ items: data });
            }, (error) => {
                console.error(error);
            })
    }

    renderItems() {
        return this.state.items.map(item =>
            <MyItem key={item} value={item} />
        )
    }

    render() {
        return <ul className="item-list">
            {this.renderItems()}
        </ul>
    }
}

export default MyList
