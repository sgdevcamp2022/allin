import Axios from '../../utils/axios'

const SignInButtonElement = () => {
  const storage = localStorage.getItem('isAutoLogin') === 'true' ? localStorage : sessionStorage
  const userStatus = storage.getItem('isLogined') === 'true'

  const goSignIn = () => {
    if (!userStatus) {
      location.href = '/sign-in'
      return
    }
    Axios.get(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/user/logout')
      .then((res) => {
        if (res.status === 200) storage.clear()
        else {
          console.error('_axios.logout : ' + '이미 로그아웃 된 상태입니다.')
        }
        storage.clear()
      })
      .catch((err) => {
        console.error('_axios.logout : ' + err)
        storage.clear()
      })
      .then(() => {
        location.reload()
      })
  }

  const loginButtonColorByStatus = userStatus
    ? 'bg-hidden text-subText border-border'
    : 'bg-point text-white border-point'
  const loginButtonClassNameByUserStatus =
    'text-xs w-28 py-2 rounded border-[1px] ' + loginButtonColorByStatus

  return (
    <button onClick={goSignIn} className={loginButtonClassNameByUserStatus}>
      {userStatus ? '로그아웃' : '로그인'}
    </button>
  )
}

export default SignInButtonElement
