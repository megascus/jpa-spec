/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.FetchType.LAZY;

/**
 * 基本型もしくは組み込み暮らすのインスタンスのコレクションを指定します。
 * 
 * コレクションがコレクションテーブルによってマッピングされる場合に指定する必要があります。
 * 
 * <pre>
 *    Example:
 *
 *    &#064;Entity public class Person {
 *       &#064;Id protected String ssn;
 *       protected String name;
 *       ...
 *       &#064;ElementCollection  
 *       protected Set&#060;String&#062; nickNames = new HashSet();
 *         ...
 *    } 
 *  </pre>
 *
 * @since Java Persistence 2.0
 */
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface ElementCollection {

    /**
     * (オプション) コレクションの要素となる基本型もしくは組み込みクラス。
     * 
     * この要素はコレクションフィールドもしくはプロパティがJavaジェネリクスを使用して定義されている場合のみオプションであり、そうでない場合は指定する必要があります。
     * ジェネリクスを使用して定義されている場合のデフォルトはコレクションのパラメタライズドタイプです。
     */
    Class targetClass() default void.class;
    
    /**
     * (オプション) コレクションを遅延ロード(LAZY)するか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * EAGER戦略は永続化プロバイダの実行時にコレクション要素を即座に取得するべきだとする要件です。
     * LAZY戦略は永続化プロバイダの実行時のヒントです。
     */
    FetchType fetch() default LAZY;
}
