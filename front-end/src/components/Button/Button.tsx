interface ButtonPartialProps {
  variant: "PRIMARY" | "SUCCESS" | "DANGER";
}

type ButtonProps = ButtonPartialProps &
  React.DetailedHTMLProps<
    React.ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  >;

const Button = ({ variant, children, ...rest }: ButtonProps) => {
  return <button {...rest}>{children}</button>;
};

export default Button;
