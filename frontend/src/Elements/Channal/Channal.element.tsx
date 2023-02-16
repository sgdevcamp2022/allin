const ChannelElement = () => {
  return (
    <div className="p-4 flex">
      <img className="rounded-full h-24" src="http://via.placeholder.com/128" alt="" />
      <div className="flex flex-col justify-between ml-8">
        <div>
          <div className="font-semibold text-2xl">제목이 들어가는 자리</div>
          <div className="font-light text-base">여기에는 추가로 설명이 들어가는 자리입니다.</div>
        </div>
        <div className="font-light text-sm">loopy</div>
      </div>
    </div>
  )
}

export default ChannelElement
