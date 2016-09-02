class Queue {
  var list:List[Node]=List()
	def EnQueue(node:Node){
    //试试这个API的效果
	  var list1:List[Node]=List(node)
	  list=list.++(list1)
	  //list.+:(node)
	}
  def DeQueue():Node={
    val result:Node=list.head
    list=list.drop(1)
    return result
  }
  def IsEmpty():Boolean={
    return list.isEmpty
  }
  def main(){
    /*
    var iter1:(String,Int)=("a",1)
    var iter2:(String,Int)=("b",2)
    var iter3:(String,Int)=("c",3)
    var tree=new Tree()
    */
    var node1=new Node()
    var node2=new Node()
    var node3=new Node()
    node1.key="a"
    node2.key="b"
    node3.key="c"
    node1.value=1
    node2.value=2
    node3.value=3
    var queue=new Queue()
    queue.EnQueue(node1)
    queue.EnQueue(node2)
    queue.EnQueue(node3)
    var result=queue.DeQueue
    println(result.key,result.value)
    result=queue.DeQueue
    println(result.key,result.value)
    result=queue.DeQueue
    println(result.key,result.value)
  }
}