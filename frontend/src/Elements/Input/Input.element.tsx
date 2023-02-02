import { ChangeEvent } from 'react'
import { RecoilState, useRecoilState } from 'recoil'

export type InputType = 'text' | 'password' | 'number'
type InputElementType = {
  textState: RecoilState<string>
  className?: string
  placeholder?: string
  type?: InputType
}

const InputElement = ({
  textState,
  className = '',
  placeholder = '',
  type = 'text',
}: InputElementType) => {
  const [text, setText] = useRecoilState(textState)

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }

  return (
    <input
      className={className + ' focus:outline-none focus:appearance-none'}
      type={type}
      value={text}
      onChange={onChange}
      placeholder={placeholder}
    />
  )
}

export default InputElement
