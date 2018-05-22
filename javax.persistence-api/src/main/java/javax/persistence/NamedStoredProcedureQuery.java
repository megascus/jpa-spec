/*******************************************************************************
 * Copyright (c) 2011 - 2015 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;  

/**
 * ストアドプロシージャとそのパラメーターと結果の型を指定し、名前を付けます。
 *
 * <p><code>NamedStoredProcedureQuery</code>アノテーションはエンティティ({@link Entity}の付いたクラス)やマップドスーパークラス({@link MappedSuperclass}の付いたクラス)に適用できます。
 *
 * <p><code>name</code>要素は{@link EntityManager#createNamedStoredProcedureQuery}メソッドに実行可能な<code>StoredProcedureQuery</code>オブジェクトを作成するときに引数として渡される名前です。
 * 名前は永続化ユニット内で共有されます。
 *
 * <p><code>procedureName</code>要素はデータベース内でのストアドプロシージャの名前です。
 *
 * <p>ストアドプロシージャのパラメーターは<code>parameters</code>要素で指定します。
 * すべてのパラメーターをストアドプロシージャのパラメーターリスト内に出現する順序で指定する必要があります。
 *
 * <p><code>resultClasses</code>要素は結果をマッピングするために使用されるクラスを示します。
 * {@link SqlResultSetMapping}アノテーションで定義されているのと同じで<code>resultSetMappings</code>要素は1つ以上の結果セットのマッピングを指定します。
 *
 * <p>複数の結果セットがある場合、それらは同じメカニズムを使用してマッピングされると想定されます。
 * たとえば、結果クラスマッピングのセットもしくは結果セットマッピングのセットのどちらかすべてを介してマッピングされます。
 * これらのマッピングの指定の順序はストアドプロシージャの呼び出しによって結果セットが戻される順序と同じでなければなりません。
 * ストアドプロシージャが1つ以上の結果セットを返し、<code>resultClasses</code>や<code>resultSetMappings</code>要素のいずれも指定されていない場合、
 * 結果セットはObjectの配列のリストとして返されます。
 * ストアドプロシージャの結果セットのマッピングに異なる戦略を組み合わせる方法は定義されていません。
 *
 * <p><code>hints</code>要素はクエリーのプロパティやヒントを指定するのに使用できます。
 * この仕様(JPA Spec.)では定義されているプロパティはプロバイダによって監視されなければなりません。
 * プロバイダによって認識されないベンダー固有のヒントは無視される必要があります。
 *
 * <p>名前付きストアドプロシージャクエリー内のすべてのパラメーターを<code>StoredProcedureParameter</code>を使用して指定する必要があります。
 *
 * @see StoredProcedureQuery
 * @see StoredProcedureParameter
 *
 * @since Java Persistence 2.1
 */
@Repeatable(NamedStoredProcedureQueries.class)
@Target({TYPE}) 
@Retention(RUNTIME)
public @interface NamedStoredProcedureQuery { 

    /**
     * {@link EntityManager}のメソッドでストアドプロシージャのクエリーオブジェクトを作成するときの参照として使用される名前。
     */
    String name();

    /** データベース内のストアドプロシージャの名前。 */
    String procedureName();

    /**
     * ストアドプロシージャのすべてのパラメーターの情報。
     * 
     * すべてのパラメーターはストアドプロシージャのパラメーターリスト内に出現する順序で指定する必要があります。
     */
    StoredProcedureParameter[] parameters() default {};

    /** 結果のマッピングに使用されるクラス。 */
    Class[] resultClasses() default {}; 

    /** メタデータとして定義される一つ以上の結果セットのマッピングの名前。 */
    String[] resultSetMappings() default {};

    /** クエリーのプロパティとヒント。(ベンダー固有のクエリーのヒントが含まれます。) */
    QueryHint[] hints() default {};

}
