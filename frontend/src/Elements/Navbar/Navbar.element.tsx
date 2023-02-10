import HomeButtonElement from '../Button/HomeButton.element'
import SignInButtonElement from '../Button/SingInButton.element'

/**
 * h-24의 높이를 사용해야합니다(tailwind 기준)
 * @returns ReactElement
 */
const NavbarElement = () => {
  return (
    <div className="flex w-screen h-24 px-20 py-10 justify-between items-center absolute top-0 z-40 backdrop-blur-sm">
      <HomeButtonElement imgClassName="w-[60%]" />
      <SignInButtonElement />
    </div>
  )
}

export default NavbarElement
