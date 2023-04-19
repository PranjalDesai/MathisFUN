package com.two2.pranjal.mathisfun
//
//import android.content.Context
//import java.util.*
//
//class ProblemFinder(var type: String?, var difficulty: String?, var mContext: Context) {
//    private val addProblem: ProblemObject
//        private get() {
//            val bound: Int
//            val q1: Int
//            val q2: Int
//            val ans: Int
//            bound = if (difficulty == mContext.resources.getString(R.string.easy)) {
//                50
//            } else if (difficulty == mContext.resources.getString(R.string.medium)) {
//                150
//            } else {
//                300
//            }
//            val random = Random()
//            q1 = 1 + random.nextInt(bound)
//            q2 = 1 + random.nextInt(bound)
//            ans = q1 + q2
//            return ProblemObject(Integer.toString(q1), Integer.toString(q2), Integer.toString(ans), "+")
//        }
//    private val subProblem: ProblemObject
//        private get() {
//            val bound: Int
//            var q1: Int
//            val q2: Int
//            val ans: Int
//            bound = if (difficulty == mContext.resources.getString(R.string.easy)) {
//                50
//            } else if (difficulty == mContext.resources.getString(R.string.medium)) {
//                100
//            } else {
//                200
//            }
//            val random = Random()
//            q1 = 1 + random.nextInt(bound)
//            q2 = 1 + random.nextInt(bound)
//            while (q2 > q1) {
//                q1 = 1 + random.nextInt(bound)
//            }
//            ans = q1 - q2
//            return ProblemObject(Integer.toString(q1), Integer.toString(q2), Integer.toString(ans), "-")
//        }
//    private val mulProblem: ProblemObject
//        private get() {
//            val bound: Int
//            val q1: Int
//            var q2: Int
//            val ans: Int
//            bound = if (difficulty == mContext.resources.getString(R.string.easy)) {
//                10
//            } else if (difficulty == mContext.resources.getString(R.string.medium)) {
//                20
//            } else {
//                30
//            }
//            val random = Random()
//            q1 = 1 + random.nextInt(bound)
//            q2 = 1 + random.nextInt(bound)
//            while (q2 > q1) {
//                q2 = 1 + random.nextInt(bound)
//            }
//            ans = q1 * q2
//            return ProblemObject(Integer.toString(q1), Integer.toString(q2), Integer.toString(ans), "*")
//        }
//    private val divProblem: ProblemObject
//        private get() {
//            val bound: Int
//            var q1: Int
//            val q2: Int
//            val ans: Int
//            bound = if (difficulty == mContext.resources.getString(R.string.easy)) {
//                10
//            } else if (difficulty == mContext.resources.getString(R.string.medium)) {
//                15
//            } else {
//                20
//            }
//            val random = Random()
//            q1 = 1 + random.nextInt(bound)
//            q2 = 1 + random.nextInt(bound)
//            ans = q1
//            q1 = q1 * q2
//            return ProblemObject(Integer.toString(q1), Integer.toString(q2), Integer.toString(ans), "/")
//        }
//    val problem: ProblemObject
//        get() = if (type == mContext.resources.getString(R.string.add)) {
//            addProblem
//        } else if (type == mContext.resources.getString(R.string.subtract)) {
//            subProblem
//        } else if (type == mContext.resources.getString(R.string.multiply)) {
//            mulProblem
//        } else if (type == mContext.resources.getString(R.string.divide)) {
//            divProblem
//        } else {
//            val r = Random()
//            val mysNumber = 1 + r.nextInt(4)
//            val mysProblem: ProblemObject
//            mysProblem = if (mysNumber == 1) {
//                addProblem
//            } else if (mysNumber == 2) {
//                subProblem
//            } else if (mysNumber == 3) {
//                mulProblem
//            } else {
//                divProblem
//            }
//            mysProblem
//        }
//}