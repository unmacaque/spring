import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'

interface PopupProps {
  title: string
  children: string | JSX.Element
  variant: string
  show: boolean
  onClose: () => void
}

export default function Popup({
  title,
  children,
  variant,
  show,
  onClose,
}: PopupProps) {
  return (
    <ToastContainer position="top-center" style={{ zIndex: 1 }}>
      <Toast
        className="d-inline-block m-1"
        bg={variant}
        show={show}
        onClose={onClose}
        autohide
        delay={10000}
      >
        <Toast.Header>
          <strong className="me-auto">{title}</strong>
        </Toast.Header>
        <Toast.Body className="text-white">{children}</Toast.Body>
      </Toast>
    </ToastContainer>
  )
}
