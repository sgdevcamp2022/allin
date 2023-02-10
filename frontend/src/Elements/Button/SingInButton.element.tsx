const SignInButtonElement = () => {
  const storage = localStorage.getItem('isAutoLogin') === 'true' ? localStorage : sessionStorage
  const userStatus = storage.getItem('isLogined') === 'true'

  const goSignIn = () => {
    if (!userStatus) {
      location.href = '/sign-in'
      return
    }
    storage.clear()
    location.reload()
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
