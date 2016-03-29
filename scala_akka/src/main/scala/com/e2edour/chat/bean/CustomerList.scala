package com.e2edour.chat.bean

import java.util


/**
  * 弄着玩啊
  *
  * @author King
  * @version 2016/3/29
  */
class CustomerList {

}

object CustomerList{

  val list :util.ArrayList[User]=new util.ArrayList

  def add(user: User)={
    if(validate(user)){

    }
  }
  def validate(user: User):Boolean={
    for(i<- 0 to list.size()){
      if(list.get(i).getId.equals(user.getId))
        return true
    }
    false
  }

}
