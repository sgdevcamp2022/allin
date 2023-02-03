import { MouseEvent, ReactElement } from 'react'

type ButtonType = 'point' | 'intactive' | 'hidden'
type ButtonSizeType = 'sm' | 'lg' | 'm'

type ButtonElementType = {
  content: string | ReactElement
  className?: string
  onClick: (e: MouseEvent<HTMLButtonElement>) => {}
  type?: ButtonType
  size?: ButtonSizeType
}

const getClassNameByType = (type: ButtonType): string => {
  switch (type) {
    case 'point':
      return 'bg-point text-white '
    case 'intactive':
      return 'bg-hidden '
    case 'hidden':
      return 'bg-hidden text-subText hover:cursor-none '
  }
}

const getClassNameBySize = (type: ButtonSizeType): string => {
  // TODO: make this
  switch (type) {
    case 'lg':
      return ''
    case 'm':
      return ''
    case 'sm':
      return ''
  }
}

const ButtonElement = ({ content, onClick, className, type, size }: ButtonElementType) => {
  const classNameByType = type ? getClassNameByType(type) : ''
  const classNameBySize = size ? getClassNameBySize(size) : ''

  return (
    <button onClick={onClick} className={classNameBySize + classNameByType + className}>
      {content}
    </button>
  )
}

export default ButtonElement
