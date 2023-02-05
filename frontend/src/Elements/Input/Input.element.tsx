import { ChangeEvent } from 'react'
import { RecoilState, useRecoilState } from 'recoil'

export type InputType = 'text' | 'password' | 'number'
type InputElementType = {
  textState: RecoilState<string>
  className?: string
  placeholder?: string
  type?: InputType
  disabled?: boolean
}

const InputElement = ({
  textState,
  className = '',
  placeholder = '',
  disabled = false,
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
      disabled={disabled}
    />
  )
}

export default InputElement
