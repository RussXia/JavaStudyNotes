package com.xzy.jvmlearning.surge;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import java.util.EnumSet;

/**
 * 程序名称规范的编译器插件:<br>
 * 如果程序命名不规范，将会输出一个编译器的WARNING信息
 * @author RuzzZZ
 * @since 31/01/2018 2:17 PM
 */
public class NameChecker {


    private final Messager messager;

    NameCheckScanner scanner = new NameCheckScanner();


    public NameChecker(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
    }

    public void checkNames(Element element){

    }


    /**
     * 名称检查的实现类，继承了JDK1.8中新提供的ElementScanner8<p>
     * 将会以Visitor模式访问抽象语法树中的元素
     */
    private class NameCheckScanner extends ElementScanner8<Void, Void>{

        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            return super.visitType(e, aVoid);
        }

        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            return super.visitExecutable(e, aVoid);
        }

        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            return super.visitVariable(e, aVoid);
        }

        private boolean checkVariableConstant(VariableElement e) {
            if (ElementKind.INTERFACE.equals(e.getEnclosingElement().getKind())) {
                return true;
            } else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            }
            String str = "dd";
            str.codePointAt(0);
            return false;
        }

    }
}
