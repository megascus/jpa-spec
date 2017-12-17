/*******************************************************************************
 * Copyright (c) 2008 - 2015 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 名前付きネイティブSQLクエリーを指定します。
 * クエリーの名前は永続化ユニット内で共有されます。
 * <code>NamedNativeQuery</code>アノテーションはエンティティ({@link Entity}の付いたクラス)やマップドスーパークラス({@link MappedSuperclass}の付いたクラス)に適用できます。
 *
 * @since Java Persistence 1.0
 */
@Repeatable(NamedNativeQueries.class)
@Target({TYPE}) 
@Retention(RUNTIME)
public @interface NamedNativeQuery { 

    /**
     * クエリーオブジェクトを作成する {@link EntityManager}のメソッドでクエリを参照するために使用される名前。
     */
    String name();

    /** SQLクエリー文字列 */
    String query();

    /** クエリーのプロパティーとヒント。(ベンダー固有のクエリーのヒントが含まれます。) */
    QueryHint[] hints() default {};

    /** 結果のクラス。 */
    Class resultClass() default void.class; 

    /** メタデータの中で定義された{@link SqlResultSetMapping}の名前。 */
    String resultSetMapping() default "";
}
