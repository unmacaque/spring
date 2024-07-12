import { useContext, useEffect, useState } from 'react'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import ListGroup from 'react-bootstrap/ListGroup'
import Modal from 'react-bootstrap/Modal'
import Row from 'react-bootstrap/Row'
import { AuthContext, IAuthContext } from 'react-oauth2-code-pkce'

export default function AccountInfo() {
  const { token } = useContext<IAuthContext>(AuthContext)

  const [accountInfo, setAccountInfo] = useState<AccountInfo | null>(null)

  function formatCurrency(num: number) {
    return num.toLocaleString(undefined, { minimumFractionDigits: 2 })
  }

  useEffect(() => {
    ;(async () => {
      if (!token) {
        return
      }
      const response = await fetch('/api/', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      if (!response.ok) {
        throw new Error(response.statusText)
      }
      const data = await response.json()
      setAccountInfo(data)
    })()
  }, [token])

  function renderItems() {
    return accountInfo?.transactions.map((item, key) => (
      <ListGroup.Item key={key}>
        <Container>
          <Row>
            <Col className="p-2">{item.receiverFullName}</Col>
            <Col
              className="p-2"
              md="auto"
            >{`${formatCurrency(item.amount.amount)} ${item.amount.currency}`}</Col>
          </Row>
        </Container>
      </ListGroup.Item>
    ))
  }

  if (!accountInfo) {
    return null
  }
  return (
    <div
      className="modal show"
      style={{ display: 'block', position: 'initial' }}
    >
      <h1>Hello {accountInfo.fullName}</h1>
      <Modal.Dialog>
        <Modal.Header style={{ fontSize: 'x-large' }}>
          <Container>
            <Row>
              <Col>Your current balance</Col>
              <Col md="auto">{`${formatCurrency(accountInfo.currentBalance.amount)} ${accountInfo.currentBalance.currency}`}</Col>
            </Row>
          </Container>
        </Modal.Header>
        <Modal.Body>
          <ListGroup>{renderItems()}</ListGroup>
        </Modal.Body>
      </Modal.Dialog>
    </div>
  )
}
