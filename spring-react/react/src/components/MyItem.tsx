interface IProps {
  value: string
}

export default function MyItem(props: IProps) {
  return <li>{props.value}</li>
}
