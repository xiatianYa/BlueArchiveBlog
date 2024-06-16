import { useGloBalStore } from './global'
import { useUserStore } from './user'
export default function(){
    return{
        userStore:useUserStore(),
        globalStore:useGloBalStore()
    }
}