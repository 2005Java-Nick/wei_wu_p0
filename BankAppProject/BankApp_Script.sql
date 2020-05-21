
drop table User_Account CASCADE;
CREATE table User_Account(

id SERIAL primary key,
username VARCHAR(25) not null,
user_password VARCHAR not null,
UNIQUE(username)

);

drop table Accounts CASCADE;
CREATE table Accounts(

id SERIAL primary key,
account_type VARCHAR(25) not null,
balance integer not null,
user_id integer
);


drop table Transactions Cascade;
CREATE table Transactions(

id SERIAL primary key,

user_account_id integer not null,
receiver_account_id integer not null,
transaction_date VARCHAR(50) not null,
transaction_type VARCHAR(25) not null,
transaction_amount numeric not null

);

ALTER table Accounts
add constraint fk_user_account foreign key (user_id) references User_Account(id) on delete cascade;

ALTER table Transactions
add constraint fk_account foreign key (user_account_id) references Accounts(id) on delete cascade;


create or replace procedure deposit(int, NUMERIC )
LANGUAGE  plpgsql
as $$
begin
	update ACCOUNTS 
	set BALANCE = BALANCE + $2
	where id = $1;

insert into transactions (transaction_date, transaction_type, user_account_id , RECEIVER_ACCOUNT_ID, TRANSACTION_AMOUNT ) values (NOW(),'deposit', (select user_id from accounts where accounts.id = $1), $1, $2);

	commit;
end;
$$;


create or replace procedure withdraw(int, NUMERIC )
LANGUAGE  plpgsql
as $$
begin
	update ACCOUNTS 
	set BALANCE = BALANCE - $2
	where id = $1;

insert into transactions (transaction_date, transaction_type, user_account_id , RECEIVER_ACCOUNT_ID, TRANSACTION_AMOUNT ) values (NOW(),'withdraw', (select user_id from accounts where accounts.id = $1), $1, $2);

	commit;
end;
$$;

create or replace procedure transfer(int, numeric, int)
LANGUAGE  plpgsql
as $$
begin
	update ACCOUNTS 
	set BALANCE = BALANCE - $2
	where id = $1;

    update ACCOUNTS 
	set BALANCE = BALANCE + $2
	where id = $3;

insert into transactions (transaction_date, transaction_type, user_account_id , RECEIVER_ACCOUNT_ID, transaction_amount ) values (NOW(),'transfer', (select user_id from accounts where accounts.id = $1), $3, $2);

	commit;
end;
$$;


create or replace function getUserTransactionHistory(int)
returns setof transactions as $$
BEGIN 
	return query select * from transactions where user_account_id = $1;
END
$$
LANGUAGE  plpgsql;

drop table accountsLog;
CREATE table accountsLOG(
id SERIAL primary key,

event_type text,
account_id integer,
userAccount_id integer,
account_type text,
balance_Before_Event numeric,
balance_After_Event numeric
);

create or replace function log_Account_Updates()
returns trigger as
$$
BEGIN 
	INSERT into accountsLOG(event_type, account_id, userAccount_id, account_type, balance_Before_Event, balance_After_Event)
	values('Balance Changed', new.id, new.user_id, new.account_type, old.balance, new.balance);
	
	
	return new;
end;
$$
LANGUAGE plpgsql;

create trigger account_Update_trigger
after UPDATE 
on accounts
for each ROW 
EXECUTE procedure log_account_updates();

ALTER TABLE public.accounts alter column balance type numeric;
ALTER TABLE public.user_account alter column user_password type varchar;



