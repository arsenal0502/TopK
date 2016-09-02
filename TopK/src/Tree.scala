import scala.util.control.Breaks._
class Tree {
  val root=new Node()
  //val loops=new Breaks
  val K=20
	def addNode(iter:(String,Int)){
    
        var node=root
        var queue=new Queue()
        breakable{
        while(node!=null){
          if(node.key==null){
            node.key=iter._1
            node.value=iter._2
           // println(node.key,node.value)
             break
          }else{
            if(node.leftchild==null){
              node.leftchild=new Node()
              queue.EnQueue(node.leftchild)
            }else if(node.rightchild==null){
              node.rightchild=new Node()
               queue.EnQueue(node.leftchild)
	      queue.EnQueue(node.rightchild)
            }else{
            queue.EnQueue(node.leftchild)
	      queue.EnQueue(node.rightchild)
	      node=queue.DeQueue()
          }
	  }
  }
  }
    //println(node.key,1)
  }
	  def sort_large(){
	    //从最后一个非叶节点进行调整
	   
	    var node=root
	    var stack=new Stack()
	    var queue=new Queue()
	      breakable{
	    while(node!=null){
	      if((node.leftchild!=null)&&(node.rightchild!=null)){
	        if((node.leftchild.key!=null)||(node.rightchild.key!=null)){
	          stack.EnStack(node)
	        }
	        if(node.leftchild.key!=null){
	           queue.EnQueue(node.leftchild)
	        }
	       if(node.rightchild.key!=null){
	         queue.EnQueue(node.rightchild)
	       }
	        if(!queue.IsEmpty){
	          node=queue.DeQueue
	        }else{
	          break
	        }
	        
	      
	      }else{
	        break
	      }
	    }
	     }
	    
	    while(!stack.IsEmpty){

	      node=stack.DeStack
	      var leftchild=node.leftchild
	      var rightchild=node.rightchild
	      if(leftchild.value>node.value){
	        var key=leftchild.key
	        var value=leftchild.value
	        leftchild.key=node.key
	        leftchild.value=node.value
	        node.key=key
	        node.value=value
	      }
	      if(rightchild.value>node.value){
	        var key=rightchild.key
	        var value=rightchild.value
	        rightchild.key=node.key
	        rightchild.value=node.value
	        node.key=key
	        node.value=value
	    }
	     }
	  }
	  def sort_small(){
	    //从最后一个非叶节点进行调整
	   
	    var node=root
	    var stack=new Stack()
	    var queue=new Queue()
	      breakable{
	    while(node!=null){
	      if((node.leftchild!=null)&&(node.rightchild!=null)){
	        if((node.leftchild.key!=null)||(node.rightchild.key!=null)){
	          stack.EnStack(node)
	        }
	        if(node.leftchild.key!=null){
	           queue.EnQueue(node.leftchild)
	        }
	       if(node.rightchild.key!=null){
	         queue.EnQueue(node.rightchild)
	       }
	        if(!queue.IsEmpty){
	          node=queue.DeQueue
	        }else{
	          break
	        }
	        
	      
	      }else{
	        break
	      }
	    }
	     }
	    
	    while(!stack.IsEmpty){

	      node=stack.DeStack
	      var leftchild=node.leftchild
	      var rightchild=node.rightchild
	      if((leftchild.key!=null)&&(node.key!=null)){
	      if(leftchild.value<node.value){
	        var key=leftchild.key
	        var value=leftchild.value
	        leftchild.key=node.key
	        leftchild.value=node.value
	        node.key=key
	        node.value=value
	      }	      	        
	      }
	      if((rightchild.key!=null)&&(node.key!=null)){
	      if(rightchild.value<node.value){
	        var key=rightchild.key
	        var value=rightchild.value
	        rightchild.key=node.key
	        rightchild.value=node.value
	        node.key=key
	        node.value=value
	    }
	    }
	     }
	  }
	  def removeNode():(String,Int)={
	    var node=root
	    var key=node.key
	    var value=node.value
	    var queue=new Queue()
	    var last_node:Node=null
	    while(node.key!=null){
	      last_node=node
	       //println(last_node.key,last_node.value)
	      queue.EnQueue(node.leftchild)
         queue.EnQueue(node.rightchild)
	      node=queue.DeQueue()
	    }
	   
	    root.key=last_node.key
	    root.value=last_node.value
	   last_node.key=null
	    last_node.value=0
	    //println(node.key,node.value)
	    return (key,value)
	  }
	  def TraversalByLevel(){
	    var node=root
	    // println(node.key,node.value)
        var queue=new Queue()
        while(node.key!=null){
          println(node.key,node.value)
	       queue.EnQueue(node.leftchild)
         queue.EnQueue(node.rightchild)
	      node=queue.DeQueue()
	    
        }
	  }
	  def number():Int={
	    var n=0
	     var node=root
        var queue=new Queue()
	    breakable{
        while(node.key!=null){
         // println(node.key)
          var k=0
           n+=1
           if(node.leftchild!=null){
             queue.EnQueue(node.leftchild)
             k=1
           }
	       if(node.rightchild!=null){
	         queue.EnQueue(node.rightchild)
	         k=1
	       }
         if(!queue.IsEmpty){
           node=queue.DeQueue()
           k=1
         }
         if(k==0){
           break
         }
	      
        }
        }
	    return n
	  }
	  def putToHeap(iter:(String,Int)){
	     var n=number
	     // println(iter,n)
	  addNode(iter)
	  n=number
	 //println(iter,1)
	 
	  if(n>K){
	    sort_small
	  removeNode
	  }
	  
	}
	def getFromHeap():Iterator[(String,Int)]={
	  var iter:Iterator[(String,Int)]=Iterator()
	  while(number>0){
	     var ita=removeNode
	     iter=iter.++(Iterator(ita))
	     //println(iter.length)
	     sort_large
	  }
	  return iter
	}
}