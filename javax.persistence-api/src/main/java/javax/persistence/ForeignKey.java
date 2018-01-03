/*******************************************************************************
 * Copyright (c) 2011 - 2017 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/
package javax.persistence;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static javax.persistence.ConstraintMode.CONSTRAINT;

/**
 * スキーマ生成が有効な場合に外部キー制約の処理を指定するために使用されます。
 * 
 * このアノテーションが指定されていない場合、永続化プロバイダのデフォルトの外部キー方式が使用されます。
 * <p>
 * <code>ConstraintMode</code>の値は外部キー制約を生成すべきかどうかを指定するために使用されます。
 * <p>
 * <code>foreignKeyDefinition</code>要素で使用される構文は、ターゲットデータベースによって外部キー制約で使用されるSQL構文に従う必要があります。
 * たとえば、次のようなものです。
 * <pre>
 * FOREIGN KEY ( &#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... )
 * REFERENCES &#060;TABLE identifier&#062; [
 *     (&#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... ) ]
 * [ ON UPDATE &#060;referential action&#062; ]
 * [ ON DELETE &#060;referential action&#062; ]
 * </pre>
 *
 * <code>ConstraintMode</code>の値が<code>CONSTRAINT</code>であるが、<code>foreignKeyDefinition</code>要素が指定されていない場合、
 * プロバイダは外部キーアノテーションが適用される結合列に最も適切に決定される更新アクションと削除アクションを持つ制約を生成します。
 *
 * @see JoinColumn
 * @see JoinColumns
 * @see MapKeyJoinColumn
 * @see MapKeyJoinColumns
 * @see PrimaryKeyJoinColumn
 * @see JoinTable
 * @see CollectionTable
 * @see SecondaryTable
 * @see AssociationOverride
 *
 * @since Java Persistence 2.1
 */
@Target({})
@Retention(RUNTIME)
public @interface ForeignKey {

    /**
     * (オプション) 外部キー制約の名前。
     * 
     * これが指定されていない場合、プロバイダが生成した名前がデフォルトです。
     */
    String name() default "";

    /**
     * (オプション) スキーマの生成が有効なときに外部キー制約を生成するべきかどうかを指定するために使用します。
     *  <p>
     *  <code>CONSTRAINT</code>の値は永続性プロバイダに外部キー制約を生成させます。
     *  <code>foreignKeyDefinition</code>要素が指定されていない場合、
     *  プロバイダは外部キーアノテーションが適用される結合列に最も適切に決定される更新アクションと削除アクションを持つ制約を生成します。
     *  <p>
     *  <code>NO_CONSTRAINT</code>の値は、制約が生成されません。
     *  <p><code>PROVIDER_DEFAULT</code>の値は、プロバイダのデフォルトのふるまいになります。
     *  (指定された結合列に対して制約が生成される場合もされない場合もあります)
     */
    ConstraintMode value() default CONSTRAINT;

    /**
     * (オプション) 外部キー制約の定義。
     */
    String foreignKeyDefinition() default "";
}
