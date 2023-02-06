import NavbarElement from '../../../Elements/Navbar/Navbar.element'
import ChatListElement from '../../../Elements/Chat/ChatList.element'
import VideoElement from '../../../Elements/Video/Video.element'

const HomeDesktopPage = () => {
  return (
    <div className="min-w-[80rem] w-screen">
      <div className="h-24"></div>
      <NavbarElement />
      <div className="flex w-full px-20 justify-between items-center">
        <VideoElement />
        <ChatListElement />
      </div>
    </div>
  )
}

export default HomeDesktopPage
