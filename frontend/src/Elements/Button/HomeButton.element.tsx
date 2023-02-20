type HomeButtonType = {
  className?: string
  imgClassName?: string
}

const HomeButtonElement = ({ className, imgClassName }: HomeButtonType) => {
  return (
    <button
      onClick={() => {
        location.href = '/'
      }}
      className={className}
    >
      <img className={imgClassName} src="/src/assets/logo.svg" alt="" />
    </button>
  )
}

export default HomeButtonElement
