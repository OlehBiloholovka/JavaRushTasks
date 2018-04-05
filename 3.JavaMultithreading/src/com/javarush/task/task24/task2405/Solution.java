package com.javarush.task.task24.task2405;

/* 
Black box
*/
public class Solution implements Action {
    public static int countActionObjects;

    private int param;

    private Action solutionAction = new Action() {
        //!!!!! Changes can be here
        //!!!!! Изменения могут быть тут

        public void someAction() {
            //!!!!! All changes have to be here
            //!!!!! Все изменения должны быть только тут

            if (param > 0){
                new FirstClass(){
                    @Override
                    public Action getDependantAction() {
                        while (param > 0){
                            System.out.println(param--);
                        }
                        super.someAction();
                        return new SecondClass(){
                            @Override
                            public void someAction() {
                                System.out.println(sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param));
                            }
                        };
                    }
                }.getDependantAction().someAction();
            }else {
                new SecondClass(){
                    @Override
                    public void someAction() {
                        System.out.println(sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param));
                    }
                }.someAction();
            }


//            super./

//            for (int i = param; i > 0; i--) {
//                System.out.println(i);
//            }
//
//            Action action = new FirstClass() {
//                @Override
//                public Action getDependantAction() {
//                    for (int i = param; i > 0; i--) {
//                        System.out.println(param = i);
//                    }
//                    super.someAction();
//
//                    return new SecondClass(){
//                        @Override
//                        public void someAction() {
//                            System.out.println(sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param));
//                        }
//                    };
//                }
//            }.getDependantAction();
//
//            action.someAction();

//            firstClass.someAction();
//            firstClass.getDependantAction().someAction();
//            firstClass.someAction();

//            new FirstClass(){
//                @Override
//                public Action getDependantAction() {
//                    for (int i = param; i > 0; i--) {
//                        System.out.println(i);
//                    }
//                    super.someAction();
//
//                    return new SecondClass(){
//                        @Override
//                        public void someAction() {
//                            System.out.println(sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM));
//                        }
//                    };
//                }
//            }.someAction();

//
//            SecondClass secondClass = new SecondClass(){
//                @Override
//                public void someAction() {
////                    super.someAction();
////                    this.someAction();
//                    System.out.println(sb.append(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM));
//                }
//            };
        }
    };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}
