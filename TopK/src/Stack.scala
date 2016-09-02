class Stack {
		var list:List[Node]=List()
		def EnStack(node:Node){
		 var list1:List[Node]=List(node)
	     list=list.++(list1)
		}
		def DeStack():Node={
		  val result:Node=list.last
		  list=list.dropRight(1)
          return result
		}
		def IsEmpty():Boolean={
		  return list.isEmpty
		}
}