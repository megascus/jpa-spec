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
 * Used to specify the handling of foreign key constraints when schema
 * generation is in effect.  If this annotation is not specified, the
 * persistence provider's default foreign key strategy will be used.
 * <p>
 * The <code>ConstraintMode</code> value is used to specify whether foreign
 * key constraints should be generated.
 * <p>
 * The syntax used in the <code>foreignKeyDefinition</code> element 
 * should follow the SQL syntax used by the target database for foreign
 * key constraints.  For example, this may be similar the following:
 * <pre>
 * FOREIGN KEY ( &#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... )
 * REFERENCES &#060;TABLE identifier&#062; [
 *     (&#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... ) ]
 * [ ON UPDATE &#060;referential action&#062; ]
 * [ ON DELETE &#060;referential action&#062; ]
 * </pre>
 *
 * When the <code>ConstraintMode</code> value is
 * <code>CONSTRAINT</code>, but the <code>foreignKeyDefinition</code>
 * element is not specified, the provider will generate foreign key
 * constraints whose update and delete actions it determines most
 * appropriate for the join column(s) to which the foreign key
 * annotation is applied.
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
     * これが指定されていない場合、プロバイダーが生成した名前がデフォルトです。
     */
    String name() default "";

    /**
     * (オプション) スキーマの生成が有効なときに外部キー制約を生成するべきかどうかを指定するために使用します。
     *  <p>
     *  <code>CONSTRAINT</code>の値は永続性プロバイダーに外部キー制約を生成させます。
     *  <code>foreignKeyDefinition</code>要素が指定されていない場合、
     *  プロバイダーは外部キーアノテーションが適用される結合列に最も適切に決定される更新アクションと削除アクションを持つ制約を生成します。
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
