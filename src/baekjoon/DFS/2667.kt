package baekjoon.완전탐색.P2667

import java.util.*

// 단지번호붙이기
var field = Array(25){ "" }
var visited = Array(25){ BooleanArray(25) }
var N = 0
val cntList = ArrayList<Int>()
val pq = PriorityQueue<Int>()
fun main(){
    val br = System.`in`.bufferedReader()
    val sb = StringBuilder()
    N = br.readLine().toInt()
    for(i in 0 until N) {
        val line = br.readLine()
        field[i] = line
    }
    var cnt = 0
    for(i in 0 until N){
        for(j in 0 until N){
            if(field[i][j] == '1' && !visited[i][j]) {
                cntList.add(0)
                dfs(i, j, cnt++)
            }
        }
    }
    sb.append("$cnt\n")
    cntList.forEach{ pq.offer(it) }
    while(pq.isNotEmpty())
        sb.append("${pq.poll()}\n")
    println(sb)

    br.close()
}

val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)
/**
 * @param x x좌표
 * @param y y좌표
 * @param cnt 아파트 수
 */
fun dfs(x: Int, y: Int, cnt: Int){
    visited[x][y] = true
    cntList[cnt]++
    for(i in 0 until 4){
        val nx = x + dx[i]
        val ny = y + dy[i]
        if(nx < 0 || nx >= N || ny < 0 || ny >= N)
            continue
        if(field[nx][ny] == '0' || visited[nx][ny])
            continue

        dfs(nx, ny, cnt)
    }
}