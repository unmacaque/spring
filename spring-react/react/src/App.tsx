import 'bootstrap/dist/css/bootstrap.min.css'
import { useContext, useState } from 'react'
import { Container } from 'react-bootstrap'
import Spinner from 'react-bootstrap/Spinner'
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { AuthContext, IAuthContext } from 'react-oauth2-code-pkce'
import AccountInfo from './components/AccountInfo'
import LoginDialog from './components/LoginDialog'
import NavigationBar from './components/NavigationBar'

function App() {
  const { token, logIn, logOut, error, idTokenData, loginInProgress } =
    useContext<IAuthContext>(AuthContext)
  const [_showDialog, setShowDialog] = useState(error !== null)
  const [logout, setLogout] = useState(false)

  function formatError(text: string | null) {
    if (text === null) {
      return text
    }
    try {
      return JSON.parse(text).error
    } catch (e) {
      return text
    }
  }

  return (
    <Container>
      {loginInProgress && (
        <Container className="spinner-loading">
          <Spinner animation="border" role="status">
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </Container>
      )}
      <ToastContainer position="top-center" style={{ zIndex: 1 }}>
        <Toast
          className="d-inline-block m-1"
          bg="danger"
          show={error !== null}
          onClose={() => setShowDialog(false)}
          autohide
          delay={10000}
        >
          <Toast.Header>
            <strong className="me-auto">Login failed</strong>
          </Toast.Header>
          <Toast.Body className="text-white">{formatError(error)}</Toast.Body>
        </Toast>
      </ToastContainer>
      <ToastContainer position="top-center" style={{ zIndex: 1 }}>
        <Toast
          className="d-inline-block m-1"
          bg="success"
          show={logout}
          onClose={() => setLogout(false)}
          autohide
          delay={5000}
        >
          <Toast.Header>
            <strong className="me-auto">Logout successful</strong>
          </Toast.Header>
          <Toast.Body>You have been logged out successfully.</Toast.Body>
        </Toast>
      </ToastContainer>
      {!token && !loginInProgress && (
        <LoginDialog onLoginClick={() => logIn()} />
      )}
      {idTokenData && (
        <>
          <NavigationBar
            username={idTokenData['sub']}
            onLogoutClick={() => {
              logOut()
              setLogout(true)
            }}
          />
          <Container className="main-content">
            <AccountInfo />
          </Container>
        </>
      )}
    </Container>
  )
}

export default App
