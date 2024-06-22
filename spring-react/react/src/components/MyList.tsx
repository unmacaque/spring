import { useEffect, useState } from 'react'
import '../App.css'
import MyItem from './MyItem'

export default function MyList() {
  const [items, setItems] = useState<string[]>([])

  useEffect(() => {
    ;(async () => {
      const response = await fetch('/api/')
      if (!response.ok) {
        throw new Error(response.statusText)
      }
      const items = await response.json()
      setItems(items)
    })()
  }, [])

  function renderItems() {
    return items.map((item, key) => <MyItem key={key} value={item} />)
  }

  return <ul className="item-list">{renderItems()}</ul>
}
