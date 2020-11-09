import React, { useEffect, useState } from 'react';
import MyItem from './MyItem'
import '../App.css';

export default function MyList() {
    const [items, setItems] = useState<string[]>([])

    useEffect(() => {
        fetch('/api/')
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.statusText);
                }
                return response.json()
            })
            .then((data: string[]) => setItems(data))
            .catch(console.error);
    }, [])

    function renderItems() {
        return (
            items.map((item, key) =>
                <MyItem key={key} value={item} />
            )
        )
    }

    return (
        <ul className="item-list">
            {renderItems()}
        </ul>
    )
}
