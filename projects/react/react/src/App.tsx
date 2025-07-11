import 'bootstrap/dist/css/bootstrap.min.css'
import { useContext, useState } from 'react'
import { Container } from 'react-bootstrap'
import Spinner from 'react-bootstrap/Spinner'
import { AuthContext, type IAuthContext } from 'react-oauth2-code-pkce'
import AccountInfo from './components/AccountInfo'
import LoginDialog from './components/LoginDialog'
import Popup from './components/MyToast'
import NavigationBar from './components/NavigationBar'

function App() {
  const { token, logIn, logOut, error, idTokenData, loginInProgress } = useContext<IAuthContext>(AuthContext)
  const [showDialog, setShowDialog] = useState(error !== null)
  const [logout, setLogout] = useState(false)

  function formatError(text: string | null) {
    if (text === null) {
      return text
    }
    try {
      return JSON.parse(text).error
    } catch {
      return text
    }
  }

  return (
    <Container>
      {loginInProgress && (
        <Container className="spinner-loading">
          <Spinner animation="border">
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </Container>
      )}
      <Popup variant="danger" title="Login failed" show={showDialog} onClose={() => setShowDialog(false)}>
        {formatError(error)}
      </Popup>
      <Popup variant="success" title="Logout successful" show={logout} onClose={() => setLogout(false)}>
        You have been logged out successfully.
      </Popup>
      {!token && !loginInProgress && <LoginDialog onLoginClick={() => logIn()} />}
      {idTokenData && (
        <>
          <NavigationBar
            username={idTokenData.sub}
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
