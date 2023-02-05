import NavbarElement from '../../../Elements/Navbar/Navbar.element'
import ChatListElement from '../../../Elements/Chat/ChatList.element'

const HomeDesktopPage = () => {
  return (
    <div className="min-w-[80rem] w-screen">
      <div className="h-24"></div>
      <NavbarElement />
      <div className="flex w-full px-20 justify-between items-center">
        <div className="flex-1">video</div>
        <ChatListElement />
      </div>
    </div>
  )
}

export default HomeDesktopPage
