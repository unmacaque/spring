import { Button } from 'react-bootstrap'
import Modal from 'react-bootstrap/Modal'

interface LoginDialogProps {
  onLoginClick: () => void
}

export default function LoginDialog({ onLoginClick }: LoginDialogProps) {
  return (
    <div className="modal show" style={{ display: 'block', position: 'initial' }}>
      <Modal.Dialog>
        <Modal.Header>
          <Modal.Title>Login required</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <p>
            You are currently not logged in. Please click the button &quot;Log In&quot; to initiate the login process.
          </p>
          <p>
            You will be redirected to your trusted login provider. Follow the steps of the login provider to return to
            this application in an authenticated state.
          </p>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="primary" onClick={onLoginClick}>
            Log In
          </Button>
        </Modal.Footer>
      </Modal.Dialog>
    </div>
  )
}
