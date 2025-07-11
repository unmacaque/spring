import { Container } from 'react-bootstrap'
import Navbar from 'react-bootstrap/Navbar'
import NavDropdown from 'react-bootstrap/NavDropdown'

import '../App.css'

interface NavigationBarProps {
  username: string
  onLogoutClick: () => void
}

export default function NavigationBar({ username, onLogoutClick }: NavigationBarProps) {
  return (
    <Navbar className="bg-body-tertiary" fixed="top">
      <Container>
        <Navbar.Brand href="#home">
          <img alt="" src="/vite.svg" width="30" height="30" className="d-inline-block align-top" /> Spring React
        </Navbar.Brand>
        <Navbar.Collapse className="justify-content-end">
          {username && (
            <>
              <Navbar.Text>Signed in as:</Navbar.Text>
              <NavDropdown title={username}>
                <NavDropdown.Item onClick={onLogoutClick}>Log Out</NavDropdown.Item>
              </NavDropdown>
            </>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}
