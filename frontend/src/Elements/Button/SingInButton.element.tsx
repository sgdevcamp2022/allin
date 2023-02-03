import { useRecoilValue } from 'recoil'
import { userStatusBooleanState } from '../../Atoms/Sign/Status.atom'

const SignInButtonElement = () => {
  const userStatus = useRecoilValue(userStatusBooleanState)

  const goSignIn = () => {
    location.href = '/sign-in'
  }

  const loginButtonColorByStatus = userStatus
    ? 'bg-hidden text-subText border-border'
    : 'bg-point text-white border-point'
  const loginButtonClassNameByUserStatus =
    'text-xs px-8 py-2 rounded border-[1px] ' + loginButtonColorByStatus

  return (
    <button onClick={goSignIn} className={loginButtonClassNameByUserStatus}>
      {userStatus ? '로그아웃' : '로그인'}
    </button>
  )
}

export default SignInButtonElement
